package org.autopair.inject;

import com.google.inject.Inject;
import com.google.inject.Provider;
import org.autopair.java.DeleteStrategy;
import org.autopair.java.SimpleDeleteStrategy;
import org.autopair.java.maven.MavenDeleteStrategy;

public class MavenDeleteStrategyProvider implements Provider<DeleteStrategy>
{
    private SimpleDeleteStrategy delegatee;

    @Inject
    public MavenDeleteStrategyProvider(SimpleDeleteStrategy delegatee)
    {
        this.delegatee = delegatee;
    }

    public DeleteStrategy get()
    {
        return new MavenDeleteStrategy(delegatee);
    }
}
