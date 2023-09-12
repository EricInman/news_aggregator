package code.processors;

import java.util.Objects;

public class DataAggregator {
  private final Processor processor;
  private final int delay;

  public DataAggregator(Processor proc, int delay) {
    this.processor = proc;
    this.delay = delay;
  }

  public Processor getProcessor() {
    return this.processor;
  }

  public int getDelay() {
    return this.delay;
  }

  @Override
  public boolean equals(Object other) {
    if (this == other) {
      return true;
    }

    if (!(other instanceof DataAggregator)) {
      return false;
    }

    DataAggregator obj = (DataAggregator) other;

    return this.processor.equals(obj.processor);
  }

  @Override
  public int hashCode() {
    return Objects.hash(processor, delay);
  }
}
