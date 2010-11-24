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

import static org.junit.Assert.*;
import org.junit.Test;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import xades4j.production.XadesFormatExtenderProfile;
import xades4j.production.XadesSignatureFormatExtender;
import xades4j.properties.CertificateValuesProperty;
import xades4j.properties.QualifyingProperty;
import xades4j.properties.RevocationValuesProperty;
import xades4j.properties.SigAndRefsTimeStampProperty;
import xades4j.properties.data.SigningTimeData;

/**
 *
 * @author Luís
 */
public class XadesVerifierImplTest extends VerifierTestBase
{
    @Test
    public void testVerifyBES() throws Exception
    {
        System.out.println("verifyBES");
        XAdESForm f = verifySignature("document.signed.bes.xml");
        assertEquals(XAdESForm.BES, f);
    }

    @Test
    public void testVerifyBESCounterSig() throws Exception
    {
        System.out.println("verifyBESCounterSig");
        XAdESForm f = verifySignature("document.signed.bes.cs.xml");
        assertEquals(XAdESForm.BES, f);
    }

    @Test
    public void testVerifyBESEnrichT() throws Exception
    {
        System.out.println("verifyBESEnrichT");

        Document doc = getDocument("document.signed.bes.xml");
        Element signatureNode = getSigElement(doc);

        XadesSignatureFormatExtender formExt = new XadesFormatExtenderProfile().getFormatExtender();
        XAdESVerificationResult res = verificationProfile.newVerifier().verify(signatureNode, formExt, XAdESForm.T);
        assertEquals(XAdESForm.BES, res.getSignatureForm());

        res = verificationProfile.newVerifier().verify(signatureNode);
        assertEquals(XAdESForm.T, res.getSignatureForm());

        outputDocument(doc, "document.verified.bes.t.xml");
    }

    @Test
    public void testVerifyBESExtrnlResEnrichC() throws Exception
    {
        System.out.println("verifyBESExtrnlResEnrichC");

        Document doc = getDocument("document.signed.bes.extres.xml");
        Element signatureNode = getSigElement(doc);

        XadesSignatureFormatExtender formExt = new XadesFormatExtenderProfile().getFormatExtender();
        XadesVerificationProfile nistVerP = new XadesVerificationProfile(VerifierTestBase.validationProviderNist);

        XAdESVerificationResult res = nistVerP.newVerifier().verify(signatureNode, formExt, XAdESForm.C);
        assertEquals(XAdESForm.BES, res.getSignatureForm());

        res = nistVerP.newVerifier().verify(signatureNode);
        assertEquals(XAdESForm.C, res.getSignatureForm());

        outputDocument(doc, "document.verified.bes.extres.c.xml");
    }

    @Test
    public void testVerifyTBES() throws Exception
    {
        System.out.println("verifyTBES");
        XAdESForm f = verifySignature("document.signed.t.bes.xml");
        assertEquals(XAdESForm.T, f);
    }

    @Test
    public void testVerifyEPES() throws Exception
    {
        System.out.println("verifyEPES");
        verificationProfile.withPolicyDocumentProvider(VerifierTestBase.policyDocumentFinder);
        XAdESForm f = verifySignature("document.signed.epes.xml");
        assertEquals(XAdESForm.EPES, f);
    }

    @Test
    public void testVerifyTEPES() throws Exception
    {
        System.out.println("verifyTEPES");
        XAdESForm f = verifySignature("document.signed.t.epes.xml");
        assertEquals(XAdESForm.T, f);
    }

    @Test
    public void testVerifyTPTCC() throws Exception
    {
        System.out.println("verifyTPtCC");

        if (!onWindowsPlatform() || null == validationProviderPtCc)
            fail("Test written for Windows-ROOT certificate repository");

        XAdESForm f = verifySignature("document.signed.t.bes.ptcc.xml",
                new XadesVerificationProfile(validationProviderPtCc));
        assertEquals(XAdESForm.T, f);
    }

    @Test
    public void testVerifyC() throws Exception
    {
        System.out.println("verifyC");
        XAdESForm f = verifySignature(
                "document.signed.c.xml",
                new XadesVerificationProfile(VerifierTestBase.validationProviderNist));
        assertEquals(XAdESForm.C, f);
    }

    @Test
    public void testVerifyCEnrichX() throws Exception
    {
        System.out.println("verifyCEnrichX");

        Document doc = getDocument("document.signed.c.xml");
        Element signatureNode = getSigElement(doc);

        XadesSignatureFormatExtender formExt = new XadesFormatExtenderProfile().getFormatExtender();
        XadesVerificationProfile p = new XadesVerificationProfile(VerifierTestBase.validationProviderNist);
        XAdESVerificationResult res = p.newVerifier().verify(signatureNode, formExt, XAdESForm.X);

        assertEquals(XAdESForm.C, res.getSignatureForm());
        assertPropElementPresent(signatureNode, SigAndRefsTimeStampProperty.PROP_NAME);

        outputDocument(doc, "document.verified.c.x.xml");
    }

    @Test
    public void testVerifyCEnrichXL() throws Exception
    {
        System.out.println("verifyCEnrichXL");

        Document doc = getDocument("document.signed.c.xml");
        Element signatureNode = getSigElement(doc);

        XadesSignatureFormatExtender formExt = new XadesFormatExtenderProfile().getFormatExtender();
        XadesVerificationProfile p = new XadesVerificationProfile(VerifierTestBase.validationProviderNist);
        XAdESVerificationResult res = p.newVerifier().verify(signatureNode, formExt, XAdESForm.X_L);

        assertEquals(XAdESForm.C, res.getSignatureForm());
        assertPropElementPresent(signatureNode, SigAndRefsTimeStampProperty.PROP_NAME);
        assertPropElementPresent(signatureNode, CertificateValuesProperty.PROP_NAME);
        assertPropElementPresent(signatureNode, RevocationValuesProperty.PROP_NAME);

        outputDocument(doc, "document.verified.c.xl.xml");
    }

    @Test(expected = UnsupportedOperationException.class)
    public void testVerifyBESCustomPropVer() throws Exception
    {
        System.out.println("verifyBESCustomPropVer");
        verificationProfile.withQualifyingPropertyVerifier(SigningTimeData.class, new QualifyingPropertyVerifier<SigningTimeData>()
        {
            @Override
            public QualifyingProperty verify(
                    SigningTimeData propData,
                    QualifyingPropertyVerificationContext ctx) throws InvalidPropertyException
            {
                throw new UnsupportedOperationException("Yeah!");
            }
        });
        verifySignature("document.signed.bes.xml");
    }

    private static void assertPropElementPresent(
            Element sigElem,
            String elemName)
    {
        NodeList props = sigElem.getElementsByTagNameNS(QualifyingProperty.XADES_XMLNS, elemName);
        assertFalse(props.getLength() == 0);
    }
}