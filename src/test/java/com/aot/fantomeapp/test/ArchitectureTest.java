package com.aot.fantomeapp.test;

import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchRule;
import com.tngtech.archunit.library.Architectures;

@AnalyzeClasses(packages = "com.aot.fantomeapp")
public class ArchitectureTest {

   @ArchTest
   static final ArchRule layer_dependencies_are_respected = Architectures.layeredArchitecture().consideringAllDependencies()
      .layer("Controllers").definedBy("com.aot.fantomeapp.controller..")
      .layer("Services").definedBy("com.aot.fantomeapp.service..")
      .layer("Repositories").definedBy("com.aot.fantomeapp.repository..")
      .layer("Test").definedBy("com.aot.fantomeapp.test..")
      .whereLayer("Controllers").mayNotBeAccessedByAnyLayer()
      .whereLayer("Services").mayOnlyBeAccessedByLayers("Controllers", "Services")
      .whereLayer("Repositories").mayOnlyBeAccessedByLayers("Services", "Test")
      ;
}
