export const crudOptions = (vm) => {
return {
  rowHandle: {
    view: {
        disabled: () => {
            return !vm.hasPermissions('${package.ModuleName}:${table.entityPath}:edit')
        }
    },
    edit: {
        disabled: () => {
            return !vm.hasPermissions('${package.ModuleName}:${table.entityPath}:edit')
        }
    },
    remove: {
        disabled: () => {
            return !vm.hasPermissions('${package.ModuleName}:${table.entityPath}:del')
        }
    },
    fixed: 'right'
  },
  options:{
    height: '100%'
  },
  columns: [
    <#-- ----------  BEGIN 字段循环遍历  ---------->
    <#list table.fields as field>
        {
        title: '${field.comment}',
        key: '${field.propertyName}',
        <#if field.propertyName == 'id'>width: 100,</#if>
        <#if field.propertyType == 'Date'>type: 'datetime', width: 152,<#else>// type: 'select',</#if>
        // dict: { url: ''}, //数据字典
        // search: { disabled: false }, // 开启查询
        // disabled: true, // 隐藏列
        form: { // 表单配置
        // disabled: true, // 禁用表单编辑
        // rules: [{ required: true, message: '请输入${field.comment}' }]
        },
        sortable: true
        }<#if field_has_next>,</#if>
    </#list>
    <#------------  END 字段循环遍历  ---------->
    ]
  }
}
