import request from '@/utils/request'

// 查询图书详情列表
export function listMeta(query) {
  return request({
    url: '/books/meta/list',
    method: 'get',
    params: query
  })
}

// 查询图书详情详细
export function getMeta(id) {
  return request({
    url: '/books/meta/' + id,
    method: 'get'
  })
}

// 新增图书详情
export function addMeta(data) {
  return request({
    url: '/books/meta',
    method: 'post',
    data: data
  })
}

// 修改图书详情
export function updateMeta(data) {
  return request({
    url: '/books/meta',
    method: 'put',
    data: data
  })
}

// 删除图书详情
export function delMeta(id) {
  return request({
    url: '/books/meta/' + id,
    method: 'delete'
  })
}
