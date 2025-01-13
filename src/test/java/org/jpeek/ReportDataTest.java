/*
 * The MIT License (MIT)
 *
 * Copyright (c) 2017-2025 Yegor Bugayenko
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included
 * in all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NON-INFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
package org.jpeek;

import java.util.HashMap;
import java.util.Map;
import org.cactoos.map.MapEntry;
import org.cactoos.map.MapOf;
import org.hamcrest.core.IsEqual;
import org.junit.jupiter.api.Test;
import org.llorllale.cactoos.matchers.Assertion;
import org.llorllale.cactoos.matchers.HasValues;
import org.llorllale.cactoos.matchers.Throws;

/**
 * Tests for {@link ReportData}.
 * @since 0.30.9
 */
final class ReportDataTest {

    @Test
    void reportsName() {
        final String name = "whatever";
        final ReportData data = new ReportData(name);
        new Assertion<>(
            "Must returns name",
            data.metric(),
            new IsEqual<>(name)
        ).affirm();
    }

    @Test
    void reportsMean() {
        final String name = "whats";
        final double mean = 0;
        final double sigma = 1;
        final ReportData data = new ReportData(name, ReportDataTest.args(), mean, sigma);
        new Assertion<>(
            "Must returns mean",
            data.mean(),
            new IsEqual<>(mean)
        ).affirm();
    }

    @Test
    void reportsSigma() {
        final String name = "whatevermetric";
        final double mean = 0;
        final double sigma = 1;
        final ReportData data = new ReportData(name, ReportDataTest.args(), mean, sigma);
        new Assertion<>(
            "Must returns sigma",
            data.sigma(),
            new IsEqual<>(sigma)
        ).affirm();
    }

    @Test
    void reportsParams() {
        final String name = "name";
        final Map<String, Object> sample = ReportDataTest.args();
        final ReportData data = new ReportData(name, sample);
        new Assertion<>(
            "Must returns args",
            data.params().entrySet(),
            new HasValues<>(sample.entrySet())
        ).affirm();
    }

    @Test
    void shouldBeImmutableWhenModifyingPassedParams() {
        final String name = "metric";
        final Map<String, Object> sample = ReportDataTest.args();
        final Map<String, Object> params = new HashMap<>(sample);
        final ReportData data = new ReportData(name, params);
        params.clear();
        new Assertion<>(
            "Must be immutable",
            data.params().entrySet().size(),
            new IsEqual<>(sample.size())
        ).affirm();
    }

    @Test
    void throwsExceptionWhenTryingToModifyParams() {
        final String name = "metrics";
        final ReportData data = new ReportData(name, new HashMap<>(ReportDataTest.args()));
        new Assertion<>(
            "Must throw an exception if retrieved is modified",
            () -> {
                data.params().clear();
                return "";
            }, new Throws<>(UnsupportedOperationException.class)
        ).affirm();
    }

    private static Map<String, Object> args() {
        return new MapOf<String, Object>(
            new MapEntry<>("a", 1),
            new MapEntry<>("b", 2)
        );
    }
}
