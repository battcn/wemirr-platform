import { defHttp } from "@/utils/http/axios";

const apiPrefix = "<#if package.ModuleName??>/${package.ModuleName}</#if>/<#if controllerMappingHyphenStyle??>${controllerMappingHyphen}<#else>manager/${table.entityPath}</#if>";

export function GetList(query) {
    return defHttp.request({
        url: apiPrefix + '/page',
        method: "get",
        params: query,
    });
}
export function AddObj(obj) {
    return defHttp.request({
        url: apiPrefix,
        method: "post",
        data: obj,
    });
}

export function UpdateObj(obj) {
    return defHttp.request({
        url: apiPrefix + `/obj.id`,
        method: "put",
        data: obj,
    });
}

export function DelObj(id) {
    return defHttp.request({
        url: apiPrefix + `/obj.id`,
        method: "delete",
        data: { id },
    });
}

