package com.serializer.raft.response;

import com.serializer.raft.RaftError;
import com.serializer.raft.RaftMessage;

public interface RaftResponse extends RaftMessage {

    /**
     * Response status.
     */
    enum Status {

        /**
         * Indicates a successful response status.
         */
        OK(1),

        /**
         * Indicates a response containing an error.
         */
        ERROR(0);

        /**
         * Returns the status for the given identifier.
         *
         * @param id The status identifier.
         * @return The status for the given identifier.
         * @throws IllegalArgumentException if {@code id} is not 0 or 1
         */
        public static Status forId(int id) {
            switch (id) {
                case 1:
                    return OK;
                case 0:
                    return ERROR;
                default:
                    break;
            }
            throw new IllegalArgumentException("invalid status identifier: " + id);
        }

        private final byte id;

        Status(int id) {
            this.id = (byte) id;
        }

        /**
         * Returns the status identifier.
         *
         * @return The status identifier.
         */
        public byte id() {
            return id;
        }
    }

    /**
     * Returns the response status.
     *
     * @return The response status.
     */
    Status status();

    /**
     * Returns the response error if the response status is {@code Status.ERROR}
     *
     * @return The response error.
     */
    RaftError error();

}
