package com.wemirr.framework.log.diff.support;

import com.wemirr.framework.log.diff.configuration.DiffLogProxyAutoConfiguration;
import com.wemirr.framework.log.diff.core.annotation.EnableDiffLog;
import org.springframework.context.annotation.AdviceMode;
import org.springframework.context.annotation.AdviceModeImportSelector;
import org.springframework.context.annotation.AutoProxyRegistrar;
import org.springframework.lang.Nullable;

/**
 * DATE 6:57 PM
 *
 * @author mzt.
 */
public class DiffLogConfigureSelector extends AdviceModeImportSelector<EnableDiffLog> {

    @Override
    @Nullable
    public String[] selectImports(AdviceMode adviceMode) {
        return switch (adviceMode) {
            case PROXY -> new String[]{AutoProxyRegistrar.class.getName(), DiffLogProxyAutoConfiguration.class.getName()};
            case ASPECTJ -> new String[]{DiffLogProxyAutoConfiguration.class.getName()};
            default -> null;
        };
    }
}