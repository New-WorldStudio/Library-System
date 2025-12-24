import request from '@/utils/request'

// 查询图书卡状态日志记录列表
export function listStatusLogs(query) {
  return request({
    url: '/statusLog/statusLogs/list',
    method: 'get',
    params: query
  })
}

// 查询图书卡状态日志记录详细
export function getStatusLogs(id) {
  return request({
    url: '/statusLog/statusLogs/' + id,
    method: 'get'
  })
}

// 新增图书卡状态日志记录
export function addStatusLogs(data) {
  return request({
    url: '/statusLog/statusLogs',
    method: 'post',
    data: data
  })
}

// 修改图书卡状态日志记录
export function updateStatusLogs(data) {
  return request({
    url: '/statusLog/statusLogs',
    method: 'put',
    data: data
  })
}

// 删除图书卡状态日志记录
export function delStatusLogs(id) {
  return request({
    url: '/statusLog/statusLogs/' + id,
    method: 'delete'
  })
}
