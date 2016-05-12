package org.opencompare;

import org.opencompare.api.java.*;
import org.opencompare.api.java.util.PCMVisitor;
import org.opencompare.api.java.value.*;

/**
 * Created by gbecan on 02/02/15.
 */
public class MyPCMPrinter implements PCMVisitor {

    private boolean isBooleanCell;

    /**
     * Print some information contained in a PCM
     * @param pcm: PCM to print
     */
    public void print(PCM pcm) {

//        // We start by listing the names of the products
//        System.out.println("--- Products ---");
//        for (Product product : pcm.getProducts()) {
//            System.out.println(product.getKeyContent());
//            
//        }
//        
//        // Then, we use a visitor to print the content of the cells that represent a boolean value
//        System.out.println("--- Boolean values ---");
//        pcm.accept(this);
//        
     // Browse the cells of the PCM
        for (Product product : pcm.getProducts()) {
          for (Feature feature : pcm.getConcreteFeatures()) {
            // Find the cell corresponding to the current feature and product
            Cell cell = product.findCell(feature);

            // Get information contained in the cell
            String content = cell.getContent();
            String rawContent = cell.getRawContent();
            Value interpretation = cell.getInterpretation();

            // Print the content of the cell
            System.out.println("(" + product.getKeyContent() + ", " + feature.getName() + ") = " + content);
          }
        }

    }

    
    // Methods for the visitor

    @Override
    public void visit(PCM pcm) {
        for (Product product : pcm.getProducts()) {
            product.accept(this);
        }
    }

    @Override
    public void visit(Feature feature) {

    }

    @Override
    public void visit(FeatureGroup featureGroup) {

    }

    @Override
    public void visit(Product product) {
        for (Cell cell : product.getCells()) {
            cell.accept(this);
        }
    }

    @Override
    public void visit(Cell cell) {
        Value interpretation = cell.getInterpretation();

        // Visit the interpretation of the cell to check if it is a boolean
        isBooleanCell = false;
        if (interpretation != null) {
            interpretation.accept(this);
        }

        // Print content of the cell if it is a boolean
        if (isBooleanCell) {
            System.out.println(cell.getContent());
        }
    }

    @Override
    public void visit(BooleanValue booleanValue) {
        isBooleanCell = true;
    }

    @Override
    public void visit(Conditional conditional) {

    }

    @Override
    public void visit(DateValue dateValue) {

    }

    @Override
    public void visit(Dimension dimension) {

    }

    @Override
    public void visit(IntegerValue integerValue) {

    }

    @Override
    public void visit(Multiple multiple) {

    }

    @Override
    public void visit(NotApplicable notApplicable) {

    }

    @Override
    public void visit(NotAvailable notAvailable) {

    }

    @Override
    public void visit(Partial partial) {

    }

    @Override
    public void visit(RealValue realValue) {

    }

    @Override
    public void visit(StringValue stringValue) {

    }

    @Override
    public void visit(Unit unit) {

    }

    @Override
    public void visit(Version version) {

    }
}
