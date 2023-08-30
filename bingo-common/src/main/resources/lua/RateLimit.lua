--[[脚本参数]]
local key = KEYS[1]
local count = tonumber(ARGV[1])
local time = tonumber(ARGV[2])

--[[调用get方法，获取数据]]
local current = redis.call('get', key)

--[[如果redis值大于指定次数，直接返回]]
if current and tonumber(current) > count then
    return tonumber(current)
end

--[[redis值 + 1，设置过期时间]]
current = redis.call('incr', key)
if tonumber(current) == 1 then
    redis.call('expire', key, time)
end
return tonumber(current)
