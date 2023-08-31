--[[脚本传入参数]]
local key = KEYS[1]
local count = tonumber(ARGV[1])
local time = tonumber(ARGV[2])

--[[调用get方法，获取数据]]
local current = redis.call('get', key)

--[[如果限流Key对应的次数 > 限制次数，放行通过]]
if current and tonumber(current) > count then
    return tonumber(current)
end

--[[限流Key对应值 + 1，设置过期时间]]
current = redis.call('incr', key)
if tonumber(current) == 1 then
    redis.call('expire', key, time)
end
return tonumber(current)
