package org.gr8conf.elasticsearch

import org.elasticsearch.action.search.SearchResponse
import org.elasticsearch.client.Client
import org.elasticsearch.client.transport.TransportClient
import org.elasticsearch.common.transport.InetSocketTransportAddress
import org.elasticsearch.common.settings.ImmutableSettings

/**
 * An example runner to connect to Elasticsearch and perform regular requests
 * using the Elasticsearch Groovy client.
 */
class ElasticsearchGroovyWorkshop {
    /**
     * The main method used to start the workshop.
     *
     * @param args Unused.
     * @throws Exception if the connection fails or a malformed request is sent
     */
    static void main(String[] args) {
        println "Connecting to Elasticsearch..."

        Client client = null

        try {
            // connect to the client
            client = connectToElasticsearch {
                cluster.name = "your-cluster-name"
            }

            // run your code against the connected client
            performRequest(client)
        }
        catch (Exception e) {
            println "An error occurred while attempting to run!"

            // just so you can see what happens
            e.printStackTrace()
        }
        finally {
            // Close the client if it was connected
            client?.close()
        }

        println "Exiting..."
    }

    /**
     * Get a {@link Client} connection to Elasticsearch.
     * <p>
     * This can either be a {@link TransportClient} or Node {@link Client}.
     *
     * @return Never {@code null}.
     * @throws NullPointerException if {@code client} is {@code null}
     */
    static Client connectToElasticsearch(Closure settings) {
        new TransportClient(
            ImmutableSettings.settingsBuilder(settings)
        ).addTransportAddress(new InetSocketTransportAddress("localhost", 9300))
    }

    /**
     * Make a request against your cluster using the {@code client}.
     *
     * @param client The connected Elasticsearch client.
     * @throws NullPointerException if {@code client} is {@code null}
     */
    static void performRequest(Client client) {
        // Search your cluster for some data
        SearchResponse response = client.search {
           indices "_all"
           source {
               query {
                   match_all { }
               }
           }
        }.actionGet()

        println "This was the response from Elasticsearch: "

        // Print each hit
        response.hits.hits.each {
            println it.sourceAsMap()
        }
    }
}

