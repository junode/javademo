<<<<<<< HEAD
package com.java7developer.chapter3;

import com.google.inject.AbstractModule;

/**
 * Code for listing 3_7 - AgentFinder interface
 */
public class AgentFinderModule extends AbstractModule {

  @Override
  protected void configure() {
    bind(AgentFinder.class).to(WebServiceAgentFinder.class);
  }

=======
package com.java7developer.chapter3;

import com.google.inject.AbstractModule;

/**
 * Code for listing 3_7 - AgentFinder interface
 */
public class AgentFinderModule extends AbstractModule {

  @Override
  protected void configure() {
    bind(AgentFinder.class).to(WebServiceAgentFinder.class);
  }

>>>>>>> ce45c9b3713495949ba406e619e7db16886d0e69
}