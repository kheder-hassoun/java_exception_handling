import java.io.IOException;

    public class DisallowedValueOfK extends IOException {
        public DisallowedValueOfK() {
            super(" disallowed value of k try again (k must be greater than zero and less than the number of rows and is odd)");
        }
    }

