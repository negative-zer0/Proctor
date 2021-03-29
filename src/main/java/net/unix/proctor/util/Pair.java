package net.unix.proctor.util;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author Unix
 * @since 29.03.2021
 */

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Pair<A, B> {

    private A first;
    private B second;

}