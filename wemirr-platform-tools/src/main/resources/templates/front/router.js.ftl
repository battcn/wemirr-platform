export default {
    router:{
        path: '${package.ModuleName}/${table.entityPath}',
        name: '${package.ModuleName}${table.entityName}',
        meta: {
            title: '${table.comment}管理',
            auth: true
        },
        component: _import('${package.ModuleName}/${table.entityPath}/page')
    },
    menu: {
        path: '/${package.ModuleName}/${table.entityPath}',
        title: '${table.comment}管理'
    }
}