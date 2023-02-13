package org.hinoob.antivirus.event;

import lombok.Getter;
import lombok.Setter;

public class CancellableEvent {

    @Setter @Getter private boolean cancelled;
}
