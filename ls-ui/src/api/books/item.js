import request from '@/utils/request'

// 查询图书物品列表
export function listItem(query) {
  return request({
    url: '/books/item/list',
    method: 'get',
    params: query
  })
}

// 查询图书物品详细
export function getItem(bookId) {
  return request({
    url: '/books/item/' + bookId,
    method: 'get'
  })
}

// 新增图书物品
export function addItem(data) {
  return request({
    url: '/books/item',
    method: 'post',
    data: data
  })
}

// 修改图书物品
export function updateItem(data) {
  return request({
    url: '/books/item',
    method: 'put',
    data: data
  })
}

// 删除图书物品
export function delItem(bookId) {
  return request({
    url: '/books/item/' + bookId,
    method: 'delete'
  })
}
