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
package org.jpeek.web;

import java.io.IOException;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.llorllale.cactoos.matchers.Assertion;
import org.takes.rq.RqFake;
import org.takes.rq.RqWithHeader;
import org.takes.rq.multipart.RqMtFake;
import org.takes.rs.RsPrint;

/**
 * Test case for {@link TkUpload}.
 * @since 0.32
 * @checkstyle JavadocMethodCheck (500 lines)
 */
final class TkUploadTest {

    @Test
    void rendersIndexPage() throws IOException {
        new Assertion<>(
            "Must upload body",
            new RsPrint(
                new TkUpload((artifact, group) -> null).act(
                    new RqMtFake(
                        new RqFake(),
                        new RqWithHeader(
                            new RqFake("POST", "/", "org.jpeek:jpeek"),
                            "Content-Disposition: form-data; name=\"coordinates\""
                        )
                    )
                )
            ).printBody(),
            Matchers.startsWith("Uploaded ")
        ).affirm();
    }

}
