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

import java.io.IOException;
import java.nio.file.Path;
import org.cactoos.iterable.Joined;

/**
 * Source code base.
 *
 * <p>There is no thread-safety guarantee.
 *
 * @since 0.1
 * @checkstyle JavadocTagsCheck (500 lines)
 */
public interface Base {

    /**
     * Take all files from the base.
     * @return The iterable of files
     * @throws IOException If fails
     */
    Iterable<Path> files() throws IOException;

    /**
     * Concat.
     */
    final class Concat implements Base {
        /**
         * Left Base.
         */
        private final Base left;

        /**
         * Left Base.
         */
        private final Base right;

        /**
         * Ctor.
         * @param one Left
         * @param two Right
         */
        public Concat(final Base one, final Base two) {
            this.left = one;
            this.right = two;
        }

        @Override
        public Iterable<Path> files() throws IOException {
            return new Joined<Path>(this.left.files(), this.right.files());
        }
    }

}
