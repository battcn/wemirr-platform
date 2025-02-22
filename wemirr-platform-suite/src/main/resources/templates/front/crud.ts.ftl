import type {
CreateCrudOptionsProps,
CreateCrudOptionsRet,
} from '@fast-crud/fast-crud';
import * as api from "./api";
import { dict } from "@fast-crud/fast-crud";
import dayjs from "dayjs";

export default function (props: CreateCrudOptionsProps): CreateCrudOptionsRet {
return {
crudOptions: {
request: {
pageRequest: async (query) => await api.pageList(query),
addRequest: async ({ form }) => await api.create(form),
editRequest: async ({ form }) => await api.modify(form),
delRequest: async ({ row }) => await api.remove(row.id),
},
toolbar: {},
actionbar: {
show: true,
buttons: {},
},
rowHandle: {
width: 270,
buttons: {

},
},
columns: {
// ----------  BEGIN 字段循环遍历  ----------
<#list columns as field>
    <#if field.list || field.pk >
        ${field.propertyName}: {
        title: '${field.comment!}',
        <#if field.propertyType == 'String'>
            type: "text",
        </#if>
        <#if field.propertyType == 'Instant'>
            type: 'datetime',
        </#if>
        <#if field.propertyType == 'Boolean'>
            type: 'dict-radio',
            // 字典配置
            dict: dict({
            data: [
            { value: true, label: "启用", color: "success" },
            { value: false, label: "禁用", color: "error" },
            ],
            }),
        </#if>
        <#if field.search>
            search: { show: true },
        <#else>
            search: { show: false },
        </#if>
        <#if field.list && !field.pk>
            column: { show: true, width: 160 },
        <#else>
            column: { show: false },
        </#if>

        <#if field.edit>
            form: {
            // 表单配置
            rules: [
            { required: false, message: "请输入${field.comment!}" },
            ],
            },
        <#else>
            form: { show: false },
        </#if>
        }

        <#if field_has_next>,</#if>
    </#if>
</#list>
// ----------  END 字段循环遍历  ----------
},
},
};
}