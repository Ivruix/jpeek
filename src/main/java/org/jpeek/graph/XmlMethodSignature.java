/*
 * The MIT License (MIT)
 *
 * Copyright (c) 2017-2023 Yegor Bugayenko
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
package org.jpeek.graph;

import com.jcabi.xml.XML;
import org.cactoos.text.Joined;
import org.cactoos.text.TextEnvelope;
import org.cactoos.text.TextOf;

/**
 * Text signature of a class method, extracted from XML Skeleton.
 * @since 0.30.9
 */
public final class XmlMethodSignature extends TextEnvelope {

    /**
     * Ctor.
     * @param clazz The class element of XML skeleton.
     * @param method The method element of XML skeleton.
     */
    public XmlMethodSignature(final XML clazz, final XML method) {
        super(
            new Joined(
                new TextOf("."),
                new TextOf(clazz.xpath("./@id").get(0)),
                new TextOf(method.xpath("@name").get(0)),
                new XmlMethodArgs(method)
            )
        );
    }
}
