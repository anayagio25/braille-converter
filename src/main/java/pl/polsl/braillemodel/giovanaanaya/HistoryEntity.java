/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pl.polsl.braillemodel.giovanaanaya;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.io.Serializable;

/**
 * HistoryEntity class is showing a historical operation with input and result
 * layout. This class is mapped to a database table using JPA annotations.
 *
 *
 * @author Giovana Anaya
 * @version 1.0
 */
@Entity
public class HistoryEntity implements Serializable {

    /**
     * The unique identifier for the history record.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    /**
     * The conversion that will be performed*
     */
    private String operation;
    /**
     * The user's input of what he wants to convert*
     */
    private String input;
    /**
     * The result obtained from the conversion*
     */
    private String result;

    private static final long serialVersionUID = 1L;

    /**
     * Gets the unique identifier for the history record.
     *
     * @return The identifier of the record.
     */
    public Long getId() {
        return id;
    }

    /**
     * Sets the unique identifier for the history record.
     *
     * @param id The identifier to set.
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Gets the type of operation performed.
     *
     * @return The operation type.
     */
    public String getOperation() {
        return operation;
    }

    /**
     * Sets the type of operation performed.
     *
     * @param operation The operation type to set.
     */
    public void setOperation(String operation) {
        this.operation = operation;
    }

    /**
     * Gets the input data for the operation.
     *
     * @return The input data.
     */
    public String getInput() {
        return input;
    }

    /**
     * Sets the input data for the operation.
     *
     * @param input The input data to set.
     */
    public void setInput(String input) {
        this.input = input;
    }

    /**
     * Gets the result obtained from the operation.
     *
     * @return The operation result.
     */
    public String getResult() {
        return result;
    }

    /**
     * Sets the result obtained from the operation.
     *
     * @param result The operation result to set.
     */
    public void setResult(String result) {
        this.result = result;
    }

    /**
     * Generates a hash code for the history record based on its identifier.
     *
     * @return The hash code.
     */
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    /**
     * Checks if this history record is equal to another object based on their
     * identifiers.
     *
     * @param object The object to compare.
     * @return True if the objects are equal, false otherwise.
     */
    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof HistoryEntity)) {
            return false;
        }
        HistoryEntity other = (HistoryEntity) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    /**
     * Returns a string representation of the history record.
     *
     * @return A string representation containing the class name and the
     * identifier.
     */
    @Override
    public String toString() {
        return "pl.polsl.braillemodel.giovanaanaya.HistoryEntity[ id=" + id + " ]";
    }
}
