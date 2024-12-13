import { defHttp } from '#/api/request';

const apiPrefix = "<#if package.ModuleName??>/${package.ModuleName}</#if>/<#if controllerMappingHyphenStyle??>${controllerMappingHyphen}<#else>manager/${table.entityPath}</#if>";

export function pageList(query) {
    return defHttp.get(apiPrefix + '/page', { params: query });
}
export function create(obj) {
    return defHttp.post(apiPrefix + `/create`,obj);
}

export function modify(obj) {
    return defHttp.put(apiPrefix + `/obj.id/modify`,obj);
}

export function remove(id) {
    return defHttp.delete(apiPrefix + `/obj.id`);
}

