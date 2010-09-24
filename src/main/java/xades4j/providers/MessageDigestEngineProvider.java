/*
 *  XAdES4j - A Java library for generation and verification of XAdES signatures.
 *  Copyright (C) 2010 Luis Goncalves.
 * 
 *  This program is free software; you can redistribute it and/or modify it under
 *  the terms of the GNU General Public License as published by the Free Software
 *  Foundation; either version 2 of the License, or any later version.
 * 
 *  This program is distributed in the hope that it will be useful, but WITHOUT
 *  ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 *  FOR A PARTICULAR PURPOSE. See the GNU General Public License for more details.
 * 
 *  You should have received a copy of the GNU General Public License along with
 *  this program; if not, write to the Free Software Foundation, Inc., 59 Temple
 *  Place, Suite 330, Boston, MA 02111-1307 USA
 */
package xades4j.providers;

import java.security.MessageDigest;
import xades4j.UnsupportedAlgorithmException;

/**
 * Interface for providers of message digest engines. A default implementation
 * is provided.
 * @see xades4j.providers.impl.DefaultMessageDigestProvider
 * @author Luís
 */
public interface MessageDigestEngineProvider
{
    /**
     * Gets a {@code MessageDigest} engine for the algorithm identified by the
     * given URI. The URIs defined in the XML-DSIG specification are used.
     *
     * @param digestAlgorithmURI the URI of the digest algorithm
     * @return the message digest engine
     * @throws UnsupportedAlgorithmException if the current provider doesn't support
     *          the specified algorithm URI or there is no provider in the platform
     *          for the corresponding algorithm name
     */
    MessageDigest getEngine(String digestAlgorithmURI) throws UnsupportedAlgorithmException;
}
