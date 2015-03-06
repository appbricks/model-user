package com.leapvest.model.user;

import org.testng.annotations.Test;

import static org.fest.assertions.api.Assertions.assertThat;

/**
 * Test RBAC Resource model class
 *
 * @see com.leapvest.model.user.Resource
 */
public class ResourceTest {

    @Test
    public void shouldTestEquality() {

        Resource someResource = Resource.create("/aa/bbb/cccc/ddddd");
        assertThat(someResource.getName()).isEqualTo("ddddd");

        assertThat(someResource.toString())
            .isEqualTo("/aa/bbb/cccc/ddddd");

        Resource sameResource = Resource.create("/aa/bbb/cccc/ddddd");

        assertThat(someResource.toString())
            .isEqualTo(sameResource.toString());

        // Resource identity is the same
        assertThat(someResource)
            .isSameAs(sameResource);

        Resource someRelativeResource = Resource.create("bbb/cccc/ddddd");
        Resource sameRelativeResource = Resource.create("bbb/cccc/ddddd");

        assertThat(someRelativeResource.toString())
            .isEqualTo(sameRelativeResource.toString());

        // Same relative paths are not identical as
        // they are relative to another resource
        assertThat(someRelativeResource.toString())
            .isNotSameAs(sameRelativeResource.toString());
    }

    @Test
    public void shouldTestInEquality() {

        Resource someResource = Resource.create("/aa/bbb/cccc/ddddd");
        Resource differentResource1 = Resource.create("/aa/xx/cccc/ddddd");
        Resource differentResource2 = Resource.create("/aa/bbb/cccc/dddddd");

        assertThat(someResource)
            .isNotEqualTo(differentResource1);
        assertThat(someResource)
            .isNotEqualTo(differentResource2);
        assertThat(differentResource1)
            .isNotEqualTo(differentResource2);
        assertThat(someResource)
            .isNotSameAs(differentResource1);
        assertThat(someResource)
            .isNotSameAs(differentResource2);
        assertThat(differentResource1)
            .isNotSameAs(differentResource2);
    }

    @Test(dependsOnMethods = { "shouldTestEquality", "shouldTestInEquality" })
    public void shouldBeSearchable() {

        Resource r0 = Resource.create("/bb");
        Resource r1 = Resource.create("/aa/bbb/cccc/ddddd");
        Resource r2 = Resource.create("/aa/xx/cccc/ddddd");
        Resource r3 = Resource.create("/aa/bbb/cccc/ddddd1");
        Resource r4 = Resource.create("/aa/xx/yy/zz/bbb/cccc/ddddd2");
        Resource r5 = Resource.create("/aa/bbb/cccc/ddddd3");
        Resource r6 = Resource.create("/aa/bbb/c11c/ddddd3");
        Resource r7 = Resource.create("/aa/bbb/c22c/ddddd3");
        Resource r8 = Resource.create("/aa/b1b/cccc/ddddd3");
        Resource r9 = Resource.create("/aa/b2b/cccc/ddddd3");
        Resource r10 = Resource.create("/aa/bbb/ee");
        Resource r11 = Resource.create("/aa/xx/yy/zz/cccc/ddddd");
        Resource r12 = Resource.create("/ff/xx/zz/cccc/ddddd");
        Resource r13 = Resource.create("/aa/bbb/cccc/dddddd");

        assertThat(Resource.search("/bb"))
            .containsOnly(r0);
        assertThat(Resource.search("/aa/bbb/cccc/ddddd"))
            .containsOnly(r1);
        assertThat(Resource.search("/aa/bbb/ee"))
            .containsOnly(r10);
        assertThat(Resource.search("**/cccc/ddddd"))
            .containsOnly(r1, r2, r11, r12);
        assertThat(Resource.search("/aa/bbb/*/ddddd3"))
                .containsOnly(r5, r6, r7);
        assertThat(Resource.search("/aa/b.b/*/ddddd3"))
                .containsOnly(r5, r6, r7, r8, r9);
        assertThat(Resource.search("**/cccc/**"))
                .containsOnly(r1, r2, r3, r4, r5, r8, r9, r11, r12, r13);
    }
}
