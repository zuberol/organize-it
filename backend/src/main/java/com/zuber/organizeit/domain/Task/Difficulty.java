package com.zuber.organizeit.domain.Task;

import lombok.AllArgsConstructor;
import lombok.Builder;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.util.List;

@Embeddable
@AllArgsConstructor
@Builder
public class Difficulty {

    //    @Column(name = "priority")
//    @Builder.Default
//    private Long priority = 1000L;

//    List<String> words; // łatwe, średnie, trudne

    //ocenianie trudnosci tylko dla root taska
    // po skonczeniu wiekszego taska wypelniamy szybka ankiete na koniec dnia
        // subtaski dostarczaja tylko dodatkowych informacji w ankiecie
            // podpowiadaja slowa jakie sie czulo podczas tworzenia taska
    // mozna mierzyc stopien confusion
        // ocenianie na podstawie
            // czy spadlo confusion po wykonaniu taska




}
