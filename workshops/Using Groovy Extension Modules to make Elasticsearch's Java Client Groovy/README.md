# Using Groovy Extension Modules to make Elasticsearch's Java Client Groovy

```json
{
  "name" : "Chris Earle",
  "company" : "Elastic",
  "title" : "Software / Support Engineer",
  "twitter" : "@pickypg",
  "github" : "pickypg"
}
```

## Description

This workshop will briefly describe the history of the official
[https://github.com/elastic/elasticsearch-groovy](Elasticsearch Groovy client)
and why it made the decisions that it made.

In doing so, it will transition into a hands-on workshop to working with
Elasticsearch with the Groovy client.

By the end, I will finish with a hackathon where I hope to add useful utilities
for performing common Elasticsearch tasks, such as reindexing and scanning/scrolling
through an entire index (or indices).

## Workshop Expectations

* You should have an some knowledge of Elastisearch
* Bring your own data
     * Feel free to use one of our demo datasets (and instructions for loading it!)
     * https://github.com/elastic/demo
* Please bring a computer if you want to write some code

## Prequisites for Coding

* Java 1.7 update 60+ or Java 1.8
* Groovy 2.3.10 or later (2.4.4 preferred)
* [Elasticsearch 1.6.0 or later installed](https://www.elastic.co/downloads/elasticsearch)
* Preferred approach to compiling Groovy
    * Gradle is the easiest way (also supplied in this directory)
    * GroovyShell is also possible if you prefer using `@Grab`
* Preferred editor ([IntelliJ will be used](https://www.jetbrains.com/idea/))
* Checkout this directory

### Directory Structure

This directory has the following structure to start with.

```
build.gradle
settings.gradle
src/
  main/
    groovy/
      org/
        gr8conf/
          elasticsearch/
            ElasticsearchGroovyWorkshop.groovy
```

