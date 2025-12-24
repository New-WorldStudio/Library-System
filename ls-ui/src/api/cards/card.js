import request from '@/utils/request'

// 查询图书卡列表
export function listCard(query) {
  return request({
    url: '/cards/card/list',
    method: 'get',
    params: query
  })
}

// 查询图书卡详细
export function getCard(id) {
  return request({
    url: '/cards/card/' + id,
    method: 'get'
  })
}

// 新增图书卡
export function addCard(data) {
  return request({
    url: '/cards/card',
    method: 'post',
    data: data
  })
}

// 修改图书卡
export function updateCard(data) {
  return request({
    url: '/cards/card',
    method: 'put',
    data: data
  })
}

// 删除图书卡
export function delCard(id) {
  return request({
    url: '/cards/card/' + id,
    method: 'delete'
  })
}
