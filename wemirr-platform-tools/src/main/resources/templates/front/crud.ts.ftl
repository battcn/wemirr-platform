import * as api from "./api";
import { useMessage } from "/@/hooks/web/useMessage";
import { compute, dict } from "@fast-crud/fast-crud";
import dayjs from "dayjs";

export default function ({ expose, searchRemote }) {
  const { notification } = useMessage();
  const { fetchReceiver, searchState } = searchRemote;
  return {
    crudOptions: {
      request: {
        pageRequest: async (query) => await api.GetList(query),
        addRequest: async ({ form }) => await api.AddObj(form),
        editRequest: async ({ form }) => await api.UpdateObj(form),
        delRequest: async ({ row }) => await api.DelObj(row.id),
      },
      toolbar: {},
      actionbar: {
        show: true,
        buttons: {},
      },
      rowHandle: {
        width: 270,
        buttons: {
          publish: {
            type: "link",
            text: "自定义按钮",
            size: "small",
            title: "自定义按钮",
            order: 4,
            async click(context) {
            },
          },
        },
      },
      columns: {
        <#-- ----------  BEGIN 字段循环遍历  ---------->
        <#list table.fields as field>
          ${field.propertyName}: {
            title: '${field.comment}',
            <#if field.keyFlag >
            type: "text",
            form: { show: false },
            column: { show: false },
            </#if>
            <#if field.columnType.getType() == 'Instant'>
            type: 'datetime',
            column: { show: true, width: 160 },
            <#elseif field.columnType.getType() == 'Instant'>
            type: 'datetime',
            column: { show: true, width: 160 },
            <#elseif field.columnType.getType() == 'Boolean'>
            type: 'dict-radio',
            column: { show: true, width: 160 },
            // 字典配置
            dict: dict({
              data: [
                { value: true, label: "启用", color: "success" },
                { value: false, label: "禁用", color: "error" },
              ],
            }),
            <#else>
            type: 'text',
            search: { show: false },
            column: { show: true, width: 160 },
            </#if>
            form: {
              // 表单配置
              rules: [
                { required: false, message: "请输入${field.comment}" },
                { min: 0, max: ${field.metaInfo.length}, message: "长度在 0 到 ${field.metaInfo.length} 个字符" },
              ]
            }
         }<#if field_has_next>,</#if>
        </#list>
        <#------------  END 字段循环遍历  ---------->
      },
    },
  };
}
