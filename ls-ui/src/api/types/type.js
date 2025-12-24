import request from '@/utils/request'

// 查询图书卡类型列表
export function listType(query) {
  return request({
    url: '/types/type/list',
    method: 'get',
    params: query
  })
}

// 查询图书卡类型详细
export function getType(id) {
  return request({
    url: '/types/type/' + id,
    method: 'get'
  })
}

// 新增图书卡类型
export function addType(data) {
  return request({
    url: '/types/type',
    method: 'post',
    data: data
  })
}

// 修改图书卡类型
export function updateType(data) {
  return request({
    url: '/types/type',
    method: 'put',
    data: data
  })
}

// 删除图书卡类型
export function delType(id) {
  return request({
    url: '/types/type/' + id,
    method: 'delete'
  })
}
