import request from '@/utils/request'

// 查询押金 / 余额流水记录列表
export function listLog(query) {
  return request({
    url: '/balance/log/list',
    method: 'get',
    params: query
  })
}

// 查询押金 / 余额流水记录详细
export function getLog(id) {
  return request({
    url: '/balance/log/' + id,
    method: 'get'
  })
}

// 新增押金 / 余额流水记录
export function addLog(data) {
  return request({
    url: '/balance/log',
    method: 'post',
    data: data
  })
}

// 修改押金 / 余额流水记录
export function updateLog(data) {
  return request({
    url: '/balance/log',
    method: 'put',
    data: data
  })
}

// 删除押金 / 余额流水记录
export function delLog(id) {
  return request({
    url: '/balance/log/' + id,
    method: 'delete'
  })
}
