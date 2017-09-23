package org.phoenixframework.core.session_factory;

import javax.sql.DataSource;

/**
 *
 *
 * @author Oleg Marchenko
 */

public interface DataSourceSupplier {

    /**
     * @return
     */
    DataSource getDataSource();
}
