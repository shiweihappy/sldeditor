/*
 * SLD Editor - The Open Source Java SLD Editor
 *
 * Copyright (C) 2016, SCISYS UK Limited
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package com.sldeditor.test.unit.ui.detail.vendor.symbol.marker.arrow;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.List;

import org.geotools.styling.ExternalGraphicImpl;
import org.geotools.styling.Mark;
import org.geotools.styling.StyleBuilder;
import org.junit.Test;
import org.opengis.filter.expression.Expression;
import org.opengis.style.GraphicalSymbol;

import com.sldeditor.common.vendoroption.VendorOptionManager;
import com.sldeditor.common.xml.ui.FieldIdEnum;
import com.sldeditor.common.xml.ui.GroupIdEnum;
import com.sldeditor.ui.detail.ColourFieldConfig;
import com.sldeditor.ui.detail.GraphicPanelFieldManager;
import com.sldeditor.ui.detail.PointFillDetails;
import com.sldeditor.ui.detail.config.FieldConfigBase;
import com.sldeditor.ui.detail.config.FieldConfigColour;
import com.sldeditor.ui.detail.config.FieldConfigCommonData;
import com.sldeditor.ui.detail.config.FieldConfigPopulate;
import com.sldeditor.ui.detail.config.FieldConfigSlider;
import com.sldeditor.ui.detail.config.FieldConfigSymbolType;
import com.sldeditor.ui.detail.vendor.geoserver.marker.arrow.FieldConfigArrow;

/**
 * The unit test for FieldConfigArrow.
 * <p>{@link com.sldeditor.ui.detail.vendor.geoserver.marker.arrow.FieldConfigArrow}
 *
 * @author Robert Ward (SCISYS)
 */
public class FieldConfigArrowTest {

    /**
     * Test method for {@link com.sldeditor.ui.detail.vendor.geoserver.marker.arrow.FieldConfigArrow#internal_setEnabled(boolean)}.
     * Test method for {@link com.sldeditor.ui.detail.vendor.geoserver.marker.arrow.FieldConfigArrow#isEnabled()}.
     */
    @Test
    public void testSetEnabled() {
        // Value only, no attribute/expression dropdown
        boolean valueOnly = true;
        FieldConfigArrow field = new FieldConfigArrow(new FieldConfigCommonData(String.class, FieldIdEnum.NAME, "test label", valueOnly), null,null,null);

        // Text field will not have been created
        boolean expectedValue = true;
        field.internal_setEnabled(expectedValue);

        assertFalse(field.isEnabled());

        // Create text field
        field.createUI();
        assertTrue(field.isEnabled());

        expectedValue = false;
        field.internal_setEnabled(expectedValue);

        assertFalse(field.isEnabled());

        // Has attribute/expression dropdown
        valueOnly = false;
        FieldConfigArrow field2 = new FieldConfigArrow(new FieldConfigCommonData(String.class, FieldIdEnum.NAME, "test label", valueOnly), null,null,null);

        // Text field will not have been created
        expectedValue = true;
        field2.internal_setEnabled(expectedValue);
        assertFalse(field2.isEnabled());

        // Create text field
        field2.createUI();

        assertTrue(field2.isEnabled());

        expectedValue = false;
        field2.internal_setEnabled(expectedValue);

        assertFalse(field2.isEnabled());
    }

    /**
     * Test method for {@link com.sldeditor.ui.detail.vendor.geoserver.marker.arrow.FieldConfigArrow#setVisible(boolean)}.
     */
    @Test
    public void testSetVisible() {
        boolean valueOnly = true;
        FieldConfigArrow field = new FieldConfigArrow(new FieldConfigCommonData(String.class, FieldIdEnum.NAME, "test label", valueOnly), null,null,null);

        boolean expectedValue = true;
        field.setVisible(expectedValue);

        field.createUI();
        expectedValue = false;
        field.setVisible(expectedValue);
    }

    /**
     * Test method for {@link com.sldeditor.ui.detail.vendor.geoserver.marker.arrow.FieldConfigArrow#generateExpression()}.
     * Test method for {@link com.sldeditor.ui.detail.vendor.geoserver.marker.arrow.FieldConfigArrow#populateExpression(java.lang.Object, org.opengis.filter.expression.Expression)}.
     * Test method for {@link com.sldeditor.ui.detail.vendor.geoserver.marker.arrow.FieldConfigArrow#setTestValue(com.sldeditor.ui.detail.config.FieldId, java.lang.String)}.
     */
    @Test
    public void testGenerateExpression() {
        boolean valueOnly = true;
        FieldConfigArrow field = new FieldConfigArrow(new FieldConfigCommonData(String.class, FieldIdEnum.NAME, "test label", valueOnly), null,null,null);

        field.populateExpression((Double)null);
        field.populateField((String)null);
        assertNull(field.getStringValue());

        // Create ui
        field.createUI();
        field.populateExpression((Double)null);
        String expectedValue = "string value";
        field.populateExpression(expectedValue);
        assertFalse(expectedValue.compareTo(field.getStringValue()) == 0);

        expectedValue = "extshape://arrow?hr=1.2&t=0.34&ab=0.5";
        field.setTestValue(null, expectedValue);

        field.populateExpression(expectedValue);
        assertTrue(expectedValue.compareTo(field.getStringValue()) == 0);
    }

    /**
     * Test method for {@link com.sldeditor.ui.detail.vendor.geoserver.marker.arrow.FieldConfigArrow#revertToDefaultValue()}.
     */
    @Test
    public void testRevertToDefaultValue() {
        boolean valueOnly = true;
        FieldConfigArrow field = new FieldConfigArrow(new FieldConfigCommonData(String.class, FieldIdEnum.NAME, "test label", valueOnly), null,null,null);

        field.revertToDefaultValue();

        field.createUI();

        field.revertToDefaultValue();
    }

    /**
     * Test method for {@link com.sldeditor.ui.detail.vendor.geoserver.marker.arrow.FieldConfigArrow#justSelected()}.
     */
    @Test
    public void testJustSelected() {
        boolean valueOnly = true;
        FieldConfigArrow field = new FieldConfigArrow(new FieldConfigCommonData(String.class, FieldIdEnum.NAME, "test label", valueOnly), null,null,null);

        field.justSelected();
    }

    /**
     * Test method for {@link com.sldeditor.ui.detail.vendor.geoserver.marker.arrow.FieldConfigArrow#createCopy(com.sldeditor.ui.detail.config.FieldConfigBase)}.
     */
    @Test
    public void testCreateCopy() {
        boolean valueOnly = true;

        class TestFieldConfigArrow extends FieldConfigArrow
        {

            public TestFieldConfigArrow(FieldConfigCommonData commonData) {
                super(commonData, null,null,null);
            }

            public FieldConfigPopulate callCreateCopy(FieldConfigBase fieldConfigBase)
            {
                return createCopy(fieldConfigBase);
            }
        }

        TestFieldConfigArrow field = new TestFieldConfigArrow(new FieldConfigCommonData(String.class, FieldIdEnum.NAME, "test label", valueOnly));
        FieldConfigArrow copy = (FieldConfigArrow) field.callCreateCopy(null);
        assertNull(copy);

        copy = (FieldConfigArrow) field.callCreateCopy(field);
        assertEquals(field.getFieldId(), copy.getFieldId());
        assertTrue(field.getLabel().compareTo(copy.getLabel()) == 0);
        assertEquals(field.isValueOnly(), copy.isValueOnly());
    }

    /**
     * Test method for {@link com.sldeditor.ui.detail.vendor.geoserver.marker.arrow.FieldConfigArrow#attributeSelection(java.lang.String)}.
     */
    @Test
    public void testAttributeSelection() {
        boolean valueOnly = true;
        FieldConfigArrow field = new FieldConfigArrow(new FieldConfigCommonData(String.class, FieldIdEnum.NAME, "test label", valueOnly), null,null,null);

        field.attributeSelection("field");
        // Does nothing
    }

    /**
     * Test method for {@link com.sldeditor.ui.detail.vendor.geoserver.marker.arrow.FieldConfigArrow#getVendorOption()}.
     */
    @Test
    public void testGetVendorOption() {
        boolean valueOnly = true;
        FieldConfigArrow field = new FieldConfigArrow(new FieldConfigCommonData(String.class, FieldIdEnum.NAME, "test label", valueOnly), null,null,null);

        assertEquals(VendorOptionManager.getInstance().getDefaultVendorOptionVersion(), field.getVendorOption());
    }

    /**
     * Test method for {@link com.sldeditor.ui.detail.vendor.geoserver.marker.arrow.FieldConfigArrow#getSymbolClass()}.
     */
    @Test
    public void testGetSymbolClass() {
        boolean valueOnly = true;
        FieldConfigArrow field = new FieldConfigArrow(new FieldConfigCommonData(String.class, FieldIdEnum.NAME, "test label", valueOnly), null,null,null);

        assertEquals(ExternalGraphicImpl.class, field.getSymbolClass());
    }

    /**
     * Test method for {@link com.sldeditor.ui.detail.vendor.geoserver.marker.arrow.FieldConfigArrow#setValue(com.sldeditor.ui.detail.GraphicPanelFieldManager, com.sldeditor.ui.detail.config.FieldConfigSymbolType, org.opengis.style.GraphicalSymbol)}.
     */
    @Test
    public void testSetValue() {
        boolean valueOnly = true;

        GraphicPanelFieldManager fieldConfigManager = null;

        Class<?> panelId = PointFillDetails.class;
        fieldConfigManager = new GraphicPanelFieldManager(panelId);

        FieldConfigArrow field = new FieldConfigArrow(new FieldConfigCommonData(String.class, FieldIdEnum.NAME, "test label", valueOnly), null,null,null);

        field.setValue(null, null, null, null, null);
        field.setValue(null, fieldConfigManager, null, null, null);

        field.createUI();
        StyleBuilder styleBuilder = new StyleBuilder();
        Mark marker1 = styleBuilder.createMark("star");
        field.setValue(null, null, null, null, marker1);
        field.setValue(null, fieldConfigManager, null, null, marker1);

        Mark marker2 = styleBuilder.createMark("wkt://POLYGON((0 0, 1 0, 1 1, 0 1, 0 0))", styleBuilder.createFill(), styleBuilder.createStroke());

        field.setValue(null, null, null, null, marker2);

        fieldConfigManager = new GraphicPanelFieldManager(panelId);

        FieldIdEnum colourFieldId = FieldIdEnum.FILL_COLOUR;
        FieldConfigColour colourField = new FieldConfigColour(new FieldConfigCommonData(panelId, colourFieldId, "", false));
        colourField.createUI();
        String expectedColourValue = "#012345";
        colourField.setTestValue(null, expectedColourValue);
        FieldIdEnum opacityFieldId = FieldIdEnum.OVERALL_OPACITY;
        double expectedOpacityValue = 0.72;
        FieldConfigSlider opacityField = new FieldConfigSlider(new FieldConfigCommonData(panelId, colourFieldId, "", false));
        opacityField.createUI();
        opacityField.populateField(expectedOpacityValue);
        FieldIdEnum symbolSelectionFieldId = FieldIdEnum.SYMBOL_TYPE;
        FieldConfigBase symbolSelectionField = new FieldConfigSymbolType(new FieldConfigCommonData(panelId, colourFieldId, "", false));
        symbolSelectionField.createUI();

        fieldConfigManager.add(colourFieldId, colourField);
        fieldConfigManager.add(opacityFieldId, opacityField);
        fieldConfigManager.add(symbolSelectionFieldId, symbolSelectionField);

        field.setValue(null, fieldConfigManager, null, null, marker2);

        File f = null;
        String filename = null;
        try {
            f = File.createTempFile(getClass().getSimpleName(), ".png");
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            if((f != null) && (f.toURI() != null))
            {
                if(f.toURI().toURL() != null)
                {
                    filename = f.toURI().toURL().toString();
                }
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        // Try unsupported symbol
        ExternalGraphicImpl externalGraphic = (ExternalGraphicImpl) styleBuilder.createExternalGraphic(filename, "png");
        field.setValue(null, fieldConfigManager, null, null, externalGraphic);

        if(f != null)
        {
            f.delete();
        }
    }

    /**
     * Test method for {@link com.sldeditor.ui.detail.vendor.geoserver.marker.arrow.FieldConfigArrow#getValue(com.sldeditor.ui.detail.GraphicPanelFieldManager, org.opengis.filter.expression.Expression, boolean, boolean)}.
     */
    @Test
    public void testGetValue() {
        StyleBuilder styleBuilder = new StyleBuilder();
        // Test it with null values
        boolean valueOnly = true;
        FieldConfigArrow field = new FieldConfigArrow(new FieldConfigCommonData(String.class, FieldIdEnum.NAME, "test label", valueOnly), null,null,null);

        assertNull(field.getStringValue());

        GraphicPanelFieldManager fieldConfigManager = null;
        Expression symbolType = null;
        List<GraphicalSymbol> actualValue = field.getValue(fieldConfigManager, symbolType, false, false);

        assertTrue(actualValue.isEmpty());

        Class<?> panelId = PointFillDetails.class;
        fieldConfigManager = new GraphicPanelFieldManager(panelId);
        String actualMarkerSymbol = "solid";
        symbolType = styleBuilder.literalExpression(actualMarkerSymbol);

        FieldIdEnum colourFieldId = FieldIdEnum.FILL_COLOUR;
        FieldConfigColour colourField = new FieldConfigColour(new FieldConfigCommonData(panelId, colourFieldId, "", false));
        colourField.createUI();
        String expectedColourValue = "#012345";
        colourField.setTestValue(null, expectedColourValue);
        FieldIdEnum opacityFieldId = FieldIdEnum.OVERALL_OPACITY;
        double expectedOpacityValue = 0.72;
        FieldConfigSlider opacityField = new FieldConfigSlider(new FieldConfigCommonData(panelId, colourFieldId, "", false));
        opacityField.createUI();
        opacityField.populateField(expectedOpacityValue);
        FieldIdEnum symbolSelectionFieldId = FieldIdEnum.SYMBOL_TYPE;
        FieldConfigBase symbolSelectionField = new FieldConfigSymbolType(new FieldConfigCommonData(panelId, colourFieldId, "", false));
        symbolSelectionField.createUI();

        fieldConfigManager.add(colourFieldId, colourField);
        fieldConfigManager.add(opacityFieldId, opacityField);
        fieldConfigManager.add(symbolSelectionFieldId, symbolSelectionField);

        // Try without setting any fields
        actualValue = field.getValue(fieldConfigManager, symbolType, false, false);
        assertNotNull(actualValue);
        assertEquals(0, actualValue.size());

        // Try with symbol type of solid
        ColourFieldConfig fillFieldConfig = new ColourFieldConfig(GroupIdEnum.FILLCOLOUR, FieldIdEnum.FILL_COLOUR, FieldIdEnum.POINT_STROKE_OPACITY, FieldIdEnum.SYMBOL_TYPE);
        ColourFieldConfig strokeFieldConfig = new ColourFieldConfig(GroupIdEnum.STROKECOLOUR, FieldIdEnum.STROKE_FILL_COLOUR, FieldIdEnum.POINT_STROKE_OPACITY, FieldIdEnum.SYMBOL_TYPE);
        FieldConfigArrow field2 = new FieldConfigArrow(new FieldConfigCommonData(String.class, FieldIdEnum.NAME, "test label", valueOnly), fillFieldConfig,strokeFieldConfig,null);

        actualValue = field2.getValue(fieldConfigManager, symbolType, false, false);
        assertNotNull(actualValue);
        assertEquals(0, actualValue.size());

        field2.createUI();

        // Try with symbol type of extshape://arrow shape
        actualMarkerSymbol = "extshape://arrow?hr=1.2&t=0.34&ab=0.5";
        field2.setTestValue(null, actualMarkerSymbol);
        symbolType = styleBuilder.literalExpression(actualMarkerSymbol);
        actualValue = field2.getValue(fieldConfigManager, symbolType, false, false);
        assertNotNull(actualValue);
        assertEquals(1, actualValue.size());
        Mark actualSymbol = (Mark) actualValue.get(0);
        assertTrue(actualSymbol.getWellKnownName().toString().compareTo(actualMarkerSymbol) == 0);
        assertNull(actualSymbol.getFill());
        assertNull(actualSymbol.getStroke());

        // Enable stroke and fill flags
        actualValue = field2.getValue(fieldConfigManager, symbolType, true, true);
        assertNotNull(actualValue);
        assertEquals(1, actualValue.size());
        actualSymbol = (Mark) actualValue.get(0);
        assertTrue(actualSymbol.getWellKnownName().toString().compareTo(actualMarkerSymbol) == 0);
        assertNotNull(actualSymbol.getFill());
        assertNotNull(actualSymbol.getStroke());
    }

    /**
     * Test method for {@link com.sldeditor.ui.detail.vendor.geoserver.marker.arrow.FieldConfigArrow#populateSymbolList(java.lang.Class, java.util.List)}.
     */
    @Test
    public void testPopulateSymbolList() {
    }

    /**
     * Test method for {@link com.sldeditor.ui.detail.vendor.geoserver.marker.arrow.FieldConfigArrow#getFill(org.opengis.style.GraphicFill, com.sldeditor.ui.detail.GraphicPanelFieldManager)}.
     */
    @Test
    public void testGetFill() {
        boolean valueOnly = true;
        FieldConfigArrow field = new FieldConfigArrow(new FieldConfigCommonData(String.class, FieldIdEnum.NAME, "test label", valueOnly), null,null,null);

        assertNull(field.getFill(null, null));
    }

    /**
     * Test method for {@link com.sldeditor.ui.detail.vendor.geoserver.marker.arrow.FieldConfigArrow#getBasePanel()}.
     */
    @Test
    public void testGetBasePanel() {
        boolean valueOnly = true;
        FieldConfigArrow field = new FieldConfigArrow(new FieldConfigCommonData(String.class, FieldIdEnum.NAME, "test label", valueOnly), null,null,null);

        assertNull(field.getBasePanel());
    }

    /**
     * Test method for {@link com.sldeditor.ui.detail.vendor.geoserver.marker.arrow.FieldConfigArrow#populateFieldOverrideMap(java.lang.Class, com.sldeditor.ui.detail.FieldEnableState)}.
     */
    @Test
    public void testPopulateFieldOverrideMap() {
    }

    /**
     * Test method for {@link com.sldeditor.ui.detail.vendor.geoserver.marker.arrow.FieldConfigArrow#getFieldList(com.sldeditor.ui.detail.GraphicPanelFieldManager)}.
     */
    @Test
    public void testGetFieldList() {
        boolean valueOnly = true;
        FieldConfigArrow field = new FieldConfigArrow(new FieldConfigCommonData(String.class, FieldIdEnum.NAME, "test label", valueOnly), null,null,null);

        assertEquals(4, field.getFieldList(null).size());
    }

    /**
     * Test method for {@link com.sldeditor.ui.detail.vendor.geoserver.marker.arrow.FieldConfigArrow#accept(org.opengis.style.GraphicalSymbol)}.
     */
    @Test
    public void testAccept() {
        boolean valueOnly = true;
        FieldConfigArrow field = new FieldConfigArrow(new FieldConfigCommonData(String.class, FieldIdEnum.NAME, "test label", valueOnly), null,null,null);

        assertFalse(field.accept(null));

        StyleBuilder styleBuilder = new StyleBuilder();

        Mark marker1 = styleBuilder.createMark("star");
        assertFalse(field.accept(marker1));

        Mark marker2 = styleBuilder.createMark("extshape://arrow?hr=1.2&t=0.34&ab=0.56");
        assertTrue(field.accept(marker2));
    }

    /**
     * Test method for {@link com.sldeditor.ui.detail.vendor.geoserver.marker.arrow.FieldConfigArrow#setUpdateSymbolListener(com.sldeditor.ui.iface.UpdateSymbolInterface)}.
     */
    @Test
    public void testSetUpdateSymbolListener() {
        boolean valueOnly = true;
        FieldConfigArrow field = new FieldConfigArrow(new FieldConfigCommonData(String.class, FieldIdEnum.NAME, "test label", valueOnly), null,null,null);

        field.setUpdateSymbolListener(null);
    }

}
