package io.hujian.graphql;

import graphql.GraphQL;

import java.util.Collections;
import java.util.Map;

/**
 * Created by hujian06 on 2017/11/2.
 *
 * the facade of the graphQl
 */
public class GraphqlFacade {

    private static final GraphqlProvider PROVIDER = new GraphqlProvider();
    private static final GraphQL GRAPH_QL = GraphQL.newGraphQL(PROVIDER.getSchema()).build();

    /**
     * query by the Graphql
     * @param ghql the query
     * @return the result
     */
    public static Map<String, Object> query(String ghql) {
        if (ghql == null || ghql.isEmpty()) {
            return Collections.emptyMap();
        }

        return GRAPH_QL.execute(ghql).getData();
    }

}
