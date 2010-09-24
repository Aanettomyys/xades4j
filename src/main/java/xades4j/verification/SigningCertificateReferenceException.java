/*
 * XAdES4j - A Java library for generation and verification of XAdES signatures.
 * Copyright (C) 2010 Luis Goncalves.
 * 
 * This program is free software; you can redistribute it and/or modify it under
 * the terms of the GNU General Public License as published by the Free Software
 * Foundation; either version 2 of the License, or any later version.
 * 
 * This program is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License along with
 * this program; if not, write to the Free Software Foundation, Inc., 59 Temple
 * Place, Suite 330, Boston, MA 02111-1307 USA
 */
package xades4j.verification;

import java.security.cert.X509Certificate;
import xades4j.properties.data.CertRef;

/**
 * Thrown during verification of the {@code SigningCertificate} property if the
 * validation of one of the certificate references fails.
 * @author Luís
 */
public class SigningCertificateReferenceException extends SigningCertificateVerificationException
{
    private final X509Certificate certificate;
    private final CertRef certificateRef;
    private final String msg;

    public SigningCertificateReferenceException(
            X509Certificate certificate,
            CertRef certificateRef,
            String msg)
    {
        this.certificate = certificate;
        this.certificateRef = certificateRef;
        this.msg = String.format("cannot verify reference for certificate %s (%s)",
                certificate.getSubjectX500Principal().getName(), msg);
    }

    public X509Certificate getCertificate()
    {
        return certificate;
    }

    public CertRef getCertificateRef()
    {
        return certificateRef;
    }

    @Override
    protected String getVerificationMessage()
    {
        return this.msg;
    }
}
