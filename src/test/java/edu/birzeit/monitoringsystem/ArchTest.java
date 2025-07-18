package edu.birzeit.monitoringsystem;

import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.core.importer.ImportOption;
import org.junit.jupiter.api.Test;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses;

class ArchTest {

    @Test
    void servicesAndRepositoriesShouldNotDependOnWebLayer() {

        JavaClasses importedClasses = new ClassFileImporter()
            .withImportOption(ImportOption.Predefined.DO_NOT_INCLUDE_TESTS)
            .importPackages("edu.birzeit.monitoringsystem");

        noClasses()
            .that()
                .resideInAnyPackage("edu.birzeit.monitoringsystem.service..")
            .or()
                .resideInAnyPackage("edu.birzeit.monitoringsystem.repository..")
            .should().dependOnClassesThat()
                .resideInAnyPackage("..edu.birzeit.monitoringsystem.web..")
        .because("Services and repositories should not depend on web layer")
        .check(importedClasses);
    }
}
