<template>
    <fs-page class="page-layout-card">
        <fs-crud ref="crudRef" v-bind="crudBinding" />
    </fs-page>
</template>

<script lang="ts">
import { defineComponent, onMounted } from "vue";
import { useFs } from "@fast-crud/fast-crud";
import createCrudOptions from "./crud";

//此处为组件定义
export default defineComponent({
    name: '${table.entityName}',
    setup() {
        const { crudRef, crudBinding, crudExpose } = useFs({ createCrudOptions, context: {} });
        // 页面打开后获取列表数据
        onMounted(() => {
            crudExpose.doRefresh();
        });
        return {
            crudBinding,
            crudRef,
        };
    },
});
</script>
