package com.serializer.raft;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Objects;

import static com.google.common.base.MoreObjects.toStringHelper;

public class PrimitiveOperation implements Serializable {

	  /**
	   * Creates a new primitive operation with a simplified identifier and a null value.
	   *
	   * @param id    the operation identifier
	   * @return the primitive operation
	   */
	  public static PrimitiveOperation operation(OperationId id) {
	    return new PrimitiveOperation(OperationId.simplify(id), null);
	  }

	  /**
	   * Creates a new primitive operation with a simplified identifier.
	   *
	   * @param id    the operation identifier
	   * @param value the operation value
	   * @return the primitive operation
	   */
	  public static PrimitiveOperation operation(OperationId id, byte[] value) {
	    return new PrimitiveOperation(OperationId.simplify(id), value);
	  }

	  protected final OperationId id;
	  protected final byte[] value;

	  public PrimitiveOperation(OperationId id, byte[] value) {
	    this.id = id;
	    this.value = value;
	  }

	  /**
	   * Returns the operation identifier.
	   *
	   * @return the operation identifier
	   */
	  public OperationId id() {
	    return id;
	  }

	  /**
	   * Returns the operation value.
	   *
	   * @return the operation value
	   */
	  public byte[] value() {
	    return value;
	  }

	  @Override
	  public int hashCode() {
	    return Objects.hash(getClass(), id, value);
	  }

	  @Override
	  public boolean equals(Object object) {
	    if (object instanceof PrimitiveOperation) {
	      PrimitiveOperation operation = (PrimitiveOperation) object;
	      return Objects.equals(operation.id, id) && Arrays.equals(operation.value, value);
	    }
	    return false;
	  }

	  @Override
	  public String toString() {
	    return toStringHelper(this)
	            .add("id", id)
	            .add("value", value != null ? value : null)
	            .toString();
	  }
}
