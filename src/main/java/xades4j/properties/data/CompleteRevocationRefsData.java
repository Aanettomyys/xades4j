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
package xades4j.properties.data;

import java.util.ArrayList;
import java.util.Collection;

/**
 *
 * @author Luís
 */
public final class CompleteRevocationRefsData implements PropertyDataObject
{
    private final Collection<CRLRef> crlRefs;

    public CompleteRevocationRefsData(Collection<CRLRef> crlRefs)
    {
        this.crlRefs = new ArrayList<CRLRef>(crlRefs);
    }

    public CompleteRevocationRefsData()
    {
        this.crlRefs = new ArrayList<CRLRef>(3);
    }

    public void addCRLRef(CRLRef ref)
    {
        this.crlRefs.add(ref);
    }

    public Collection<CRLRef> getCrlRefs()
    {
        return crlRefs;
    }
}
