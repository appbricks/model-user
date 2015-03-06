package com.leapvest.repository.user;

import com.leapvest.TestContext;
import com.leapvest.model.user.Role;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.Test;

import javax.inject.Inject;

@ContextConfiguration(classes = { TestContext.class })
public class RoleRepositoryTest
    extends AbstractTestNGSpringContextTests {

    @Inject
    private RoleRepository roleRepository;

    @Test
    public void shouldTestCreation()
        throws Exception {

        Role testAdmin = new Role("test_admin");
        testAdmin.setDescription("Test administrator role");
        testAdmin.allow("**", true);
        roleRepository.save(testAdmin);

        Role testUser = new Role("test_user");
        testUser.setDescription("Test user role");
        testUser.allow("**/user/**", true);
        roleRepository.save(testUser);

        Role testReadOnlyUser = new Role("test_readonly_user");
        testReadOnlyUser.setDescription("Test read only user role");
        testReadOnlyUser.allow("**/user/**", true);
        testReadOnlyUser.deny("**/user/**/update", true);
        roleRepository.save(testReadOnlyUser);
    }

    @Test(dependsOnMethods = { "shouldTestCreation" })
    public void shouldTestDeletion()
        throws Exception {

        Role testAdmin = roleRepository.findByName("test_admin");
        if (testAdmin != null)
            roleRepository.delete(testAdmin);

        Role testUser = roleRepository.findByName("test_user");
        if (testUser != null)
            roleRepository.delete(testUser);

        Role testReadOnlyUser = roleRepository.findByName("test_readonly_user");
        if (testReadOnlyUser != null)
            roleRepository.delete(testReadOnlyUser);
    }
}
