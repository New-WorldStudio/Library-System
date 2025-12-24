import request from '@/utils/request'

// 查询办卡申请列表
export function listApplication(query) {
  return request({
    url: '/applications/application/list',
    method: 'get',
    params: query
  })
}

// 查询办卡申请详细
export function getApplication(id) {
  return request({
    url: '/applications/application/' + id,
    method: 'get'
  })
}

// 新增办卡申请
export function addApplication(data) {
  return request({
    url: '/applications/application',
    method: 'post',
    data: data
  })
}

// 修改办卡申请
export function updateApplication(data) {
  return request({
    url: '/applications/application',
    method: 'put',
    data: data
  })
}

// 删除办卡申请
export function delApplication(id) {
  return request({
    url: '/applications/application/' + id,
    method: 'delete'
  })
}
