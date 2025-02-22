import { defHttp } from '#/api/request';

const apiPrefix = "/<#if moduleName??>${moduleName}</#if>/<#if businessName??>${businessName}</#if>";

export function pageList(query : any) {
return defHttp.get(apiPrefix + '/page', { params: query });
}
export function create(obj  : any) {
return defHttp.post(apiPrefix + `/create`,obj);
}

export function modify(row : any) {
return defHttp.put(apiPrefix + `<#noparse>/${row.id}/modify</#noparse>`, row);
}

export function remove(id : any) {
return defHttp.delete(apiPrefix + `<#noparse>/${id}</#noparse>`);
}