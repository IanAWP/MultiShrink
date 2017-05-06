package io.github.ianawp.multishrink.di;

import javax.inject.Inject;

import dagger.Component;
import io.github.ianawp.multishrink.store.db.DBJob;

/**
 * Created by IanAWP on 5/05/2017.
 */
@Component(
        dependencies = ApplicationComponent.class
)
@DBJobScope
public interface DBJobComponent {
        void Inject(DBJob job);
}
