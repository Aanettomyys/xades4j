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
package xades4j.production;

import org.junit.Test;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import xades4j.providers.impl.ValidationDataFromCertValidationProvider;
import xades4j.providers.ValidationDataProvider;
import xades4j.verification.VerifierTestBase;

/**
 *
 * @author Luís
 */
public class SignerCTest extends SignerTestBase
{
    @Test
    public void testSignC() throws Exception
    {
        System.out.println("signC");

        Document doc = getTestDocument();
        Element elemToSign = doc.getDocumentElement();

        ValidationDataProvider vdp = new ValidationDataFromCertValidationProvider(VerifierTestBase.validationProviderNist);
        SignerC signer = (SignerC)new XadesCSigningProfile(keyingProviderNist, vdp).newSigner();
        new Enveloped(signer).sign(elemToSign);

        outputDocument(doc, "document.signed.c.xml");
    }
}
