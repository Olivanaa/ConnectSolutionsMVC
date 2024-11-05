package br.com.fiap.connectionsolutions_ia.excel;

import java.util.ArrayList;
import java.util.List;

public class ImportError {
    private List<ImportError> importErrors = new ArrayList<>();
    private int rowIndex;
    private String errorMessage;

    public ImportError(int rowIndex, String errorMessage) {
        this.rowIndex = rowIndex;
        this.errorMessage = errorMessage;
    }

    public List<ImportError> getImportErrors() {
        return importErrors;
    }

    public int getRowIndex() {
        return rowIndex;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    @Override
    public String toString() {
        return "Erro na linha " + rowIndex + ": " + errorMessage;
    }
}

