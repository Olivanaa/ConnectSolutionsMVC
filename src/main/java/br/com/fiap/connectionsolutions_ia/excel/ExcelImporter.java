package br.com.fiap.connectionsolutions_ia.excel;


import br.com.fiap.connectionsolutions_ia.cliente.Cliente;
import br.com.fiap.connectionsolutions_ia.cliente.ClienteRepository;
import br.com.fiap.connectionsolutions_ia.endereco.Endereco;
import br.com.fiap.connectionsolutions_ia.endereco.EnderecoRepository;
import br.com.fiap.connectionsolutions_ia.interesse.Interesse;
import br.com.fiap.connectionsolutions_ia.interesse.InteresseRepository;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.context.annotation.Configuration;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import org.apache.poi.ss.usermodel.*;

import java.util.Date;

@Configuration
public class ExcelImporter {

    private final ClienteRepository clienteRepository;
    private final EnderecoRepository enderecoRepository;
    private final InteresseRepository interesseRepository;

    private List<ImportError> importErrors = new ArrayList<>();


    public ExcelImporter(ClienteRepository clienteRepository, EnderecoRepository enderecoRepository, InteresseRepository interesseRepository) {
        this.clienteRepository = clienteRepository;
        this.enderecoRepository = enderecoRepository;
        this.interesseRepository = interesseRepository;
    }

    public List<Cliente> importarClientes(MultipartFile arquivoExcel) throws IOException {
        List<Cliente> clientes = new ArrayList<>();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        try (Workbook workbook = new XSSFWorkbook(arquivoExcel.getInputStream())) {
            Sheet sheet = workbook.getSheetAt(0);

            for (Row row : sheet) {
                if (row.getRowNum() == 0) continue; // Ignora o cabeçalho

                Cliente cliente = new Cliente();

                try {
                    cliente.setNome(getCellValueAsString(row.getCell(0)));
                } catch (Exception e) {
                    importErrors.add(new ImportError(row.getRowNum() + 1, "Erro no campo nome: " + e.getMessage()));
                    continue;
                }

                try {
                    cliente.setEmail(getCellValueAsString(row.getCell(1)));
                } catch (Exception e) {
                    importErrors.add(new ImportError(row.getRowNum() + 1, "Erro no campo email: " + e.getMessage()));
                    continue;
                }

                try {
                    cliente.setTelefone(getCellValueAsString(row.getCell(2)).replaceAll("[^0-9]", ""));
                } catch (Exception e) {
                    importErrors.add(new ImportError(row.getRowNum() + 1, "Erro no campo telefone: " + e.getMessage()));
                    continue;
                }

                try {
                    cliente.setDtaNascimento(parseLocalDate(row.getCell(3), formatter));
                } catch (DateTimeParseException e) {
                    importErrors.add(new ImportError(row.getRowNum() + 1, "Data de nascimento inválida: " + e.getMessage()));
                    continue;
                }

                try {
                    cliente.setDtaCadastro(parseLocalDate(row.getCell(4), formatter));
                } catch (DateTimeParseException e) {
                    importErrors.add(new ImportError(row.getRowNum() + 1, "Data de cadastro inválida: " + e.getMessage()));
                    continue;
                }

                try {
                    cliente.setSegmentoMercado(getCellValueAsString(row.getCell(5)));
                } catch (Exception e) {
                    importErrors.add(new ImportError(row.getRowNum() + 1, "Erro no campo segmento de mercado: " + e.getMessage()));
                    continue;
                }

                try {
                    cliente.setDtaUltimaInteracao(parseLocalDate(row.getCell(6), formatter));
                } catch (DateTimeParseException e) {
                    importErrors.add(new ImportError(row.getRowNum() + 1, "Data de última interação inválida: " + e.getMessage()));
                    continue;
                }

                Endereco endereco = new Endereco();
                try {
                    endereco.setCep(getCellValueAsString(row.getCell(7)).replaceAll("[^0-9]", ""));
                } catch (Exception e) {
                    importErrors.add(new ImportError(row.getRowNum() + 1, "Erro no campo CEP: " + e.getMessage()));
                    continue;
                }

                try {
                    endereco.setLogradouro(getCellValueAsString(row.getCell(8)));
                } catch (Exception e) {
                    importErrors.add(new ImportError(row.getRowNum() + 1, "Erro no campo logradouro: " + e.getMessage()));
                    continue;
                }

                try {
                    endereco.setNumero(getCellValueAsString(row.getCell(9)));
                } catch (Exception e) {
                    importErrors.add(new ImportError(row.getRowNum() + 1, "Erro no campo número: " + e.getMessage()));
                    continue;
                }

                try {
                    endereco.setComplemento(getCellValueAsString(row.getCell(10)));
                } catch (Exception e) {
                    importErrors.add(new ImportError(row.getRowNum() + 1, "Erro no campo complemento: " + e.getMessage()));
                }

                try {
                    endereco.setCidade(getCellValueAsString(row.getCell(11)));
                } catch (Exception e) {
                    importErrors.add(new ImportError(row.getRowNum() + 1, "Erro no campo cidade: " + e.getMessage()));
                    continue;
                }

                try {
                    endereco.setEstado(getCellValueAsString(row.getCell(12)));
                } catch (Exception e) {
                    importErrors.add(new ImportError(row.getRowNum() + 1, "Erro no campo estado: " + e.getMessage()));
                    continue;
                }

                try {
                    endereco.setBairro(getCellValueAsString(row.getCell(13)));
                } catch (Exception e) {
                    importErrors.add(new ImportError(row.getRowNum() + 1, "Erro no campo bairro: " + e.getMessage()));
                    continue;
                }

                try {
                    endereco.setTipo(getCellValueAsString(row.getCell(14)));
                } catch (Exception e) {
                    importErrors.add(new ImportError(row.getRowNum() + 1, "Erro no campo tipo de endereço: " + e.getMessage()));
                    continue;
                }

                Interesse interesse = new Interesse();
                try {
                    interesse.setDescricao(getCellValueAsString(row.getCell(15)));
                } catch (Exception e) {
                    importErrors.add(new ImportError(row.getRowNum() + 1, "Erro no campo descrição de interesse: " + e.getMessage()));
                    continue;
                }

                try {
                    interesse.setDtInteracao(parseLocalDate(row.getCell(16), formatter));
                } catch (DateTimeParseException e) {
                    importErrors.add(new ImportError(row.getRowNum() + 1, "Data de interação inválida: " + e.getMessage()));
                    continue;
                }

                cliente.setEndereco(endereco);
                cliente.setInteresse(interesse);
                clientes.add(cliente);
            }
        }

        salvarClientes(clientes);

        return clientes;
    }

    public List<ImportError> getImportErrors() {
        return importErrors;
    }

    public void salvarClientes(List<Cliente> clientes) {
        for (Cliente cliente : clientes) {
            try {
                Endereco endereco = cliente.getEndereco();
                Interesse interesse = cliente.getInteresse();

                if (endereco != null) {
                    cliente.setEndereco(enderecoRepository.save(endereco));
                }
                if (interesse != null) {
                    cliente.setInteresse(interesseRepository.save(interesse));
                }

                clienteRepository.save(cliente);

            } catch (ConstraintViolationException e) {
                for (ConstraintViolation<?> violation : e.getConstraintViolations()) {
                    String message = String.format("Erro no campo %s: %s",
                            violation.getPropertyPath(), violation.getMessage());
                    importErrors.add(new ImportError(clientes.indexOf(cliente) + 1, message));
                }

            } catch (DataIntegrityViolationException e) {
                String message = "Erro de integridade de dados: " + e.getMessage();
                importErrors.add(new ImportError(clientes.indexOf(cliente) + 1, message));

            } catch (Exception e) {
                String message = "Erro inesperado ao salvar cliente: " + e.getMessage();
                importErrors.add(new ImportError(clientes.indexOf(cliente) + 1, message));
            }
        }
    }



    private String getCellValueAsString(Cell cell) {
        if (cell == null) return "";
        switch (cell.getCellType()) {
            case STRING:
                return cell.getStringCellValue();
            case NUMERIC:
                return String.valueOf((int) cell.getNumericCellValue());
            case BOOLEAN:
                return String.valueOf(cell.getBooleanCellValue());
            case BLANK:
                return "";
            case FORMULA:
                return cell.getCellFormula();
            default:
                return "";
        }
    }

    private LocalDate parseLocalDate(Cell cell, DateTimeFormatter formatter) {
        if (cell == null || cell.getCellType() == CellType.BLANK) return null;

        if (cell.getCellType() == CellType.NUMERIC) {
            Date date = cell.getDateCellValue();
            return date.toInstant().atZone(java.time.ZoneId.systemDefault()).toLocalDate();
        }

        String dateString = getCellValueAsString(cell).trim();
        DateTimeFormatter adjustedFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        try {
            return LocalDate.parse(dateString, adjustedFormatter);
        } catch (DateTimeParseException e) {
            System.out.println("Erro ao fazer parse da data: " + dateString);
            throw e;
        }
    }
}