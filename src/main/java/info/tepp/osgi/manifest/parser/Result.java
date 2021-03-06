package info.tepp.osgi.manifest.parser;

import info.tepp.osgi.manifest.parser.Tuple.Tuple2;

/**
 * Result of a parser.
 * It can be either {@linkplain info.tepp.osgi.manifest.parser.Result.Success}
 * or {@linkplain info.tepp.osgi.manifest.parser.Result.Failure}.
 * @param <T>
 */
public abstract class Result<T> {
    private Result() {}

    public static final class Failure extends Result {
        public final String message;

        public Failure(String message) {
            assert message != null: "Message cannot be null!";
            this.message = message;
        }

        public static Failure of(String message) {
            return new Failure(message);
        }

        @Override
        public String toString() {
            return "Failure: " + message;
        }

        @SuppressWarnings("unchecked")
        public <T> Result<T> asResult() {
            return (Result<T>) this;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Failure failure = (Failure) o;
            return message.equals(failure.message);
        }

        @Override
        public int hashCode() {
            return message.hashCode();
        }
    }

    public static class Success<T> extends Result<T> {
        public final T value;
        public final CharSequence rest;

        public Success(T value, CharSequence rest) {
            this.value = value;
            this.rest = rest;
        }

        public static <T> Success<T> of(T value, CharSequence rest) {
            return new Success<T>(value, rest);
        }

        public static <L, R> Success<Tuple2<L, R>> of(L left, R right, CharSequence rest) {
            return Success.of(Tuple.of(left, right), rest);
        }

        @Override
        public String toString() {
            return "Success: " + value + ", \"" + rest + "\"";
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Success success = (Success) o;

            if (!rest.equals(success.rest)) return false;
            if (value == null) {
                if (success.value != null) return false;
            } else {
                if (!value.equals(success.value)) return false;
            }

            return true;
        }

        @Override
        public int hashCode() {
            int result = value != null ? value.hashCode() : 0;
            result = 31 * result + rest.hashCode();
            return result;
        }
    }
}
