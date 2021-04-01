package com.github.neukrang.mybookmark.lomboktest;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class LombokTest {

    @Test
    public void testFinalMember() {
        String data = "test";

        FinalMemberTestVO finalMemberTestVO = new FinalMemberTestVO(data);

        assertThat(finalMemberTestVO.getData()).isEqualTo(data);
    }

    @Test
    public void testNonFinalMember() {
        String data = "test";

        NonFinalMemberTestVO nonFinalMemberTestVO = new NonFinalMemberTestVO();
        nonFinalMemberTestVO.setData(data);

        assertThat(nonFinalMemberTestVO.getData()).isEqualTo(data);
    }
}
