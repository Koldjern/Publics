package com.logic.math;

import java.sql.Date;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

import com.model.entities.Agreement;
import com.model.entities.Invoice;
import com.rki.rki.Rating;
//jakob
public class LoanCalculator {
// En invoice indeholder parametre fra Agreement klassen og vi initialisere med en række variabler fra Agreement
    public static Invoice[] låneBeregner(Agreement agreement) {
        Invoice [] Betalinger = new Invoice[agreement.getFixedTerms()];
// Kalder på renteProcent fra Agreement klassen for at få renten
        double rente = renteProcent(agreement);
// Her beregnes det samlede lånebeløb ved at tage bilens pris og minus indskuddet
        double totalBeløb = agreement.getVehicle().getPrice() - agreement.getStartValue();
        double månedligBetaling = fastYdelse(totalBeløb, rente, agreement.getFixedTerms());
        int terminer = agreement.getFixedTerms();
        agreement.setEndPrice (terminer * månedligBetaling);
        Date firstDate = agreement.getStart();
        double primoGæld = totalBeløb;
        for (int i = 1; i <= terminer; i++) {
// Her finder vi start datoen for et lån og slut datoen, slutdatoen bliver udregnet ved at plusse antal terminer på start datoen
            LocalDate firstPlus = firstDate.toLocalDate();
// Konvertere det om til måneder ud fra antal terminer
            firstPlus = firstPlus.plus(1, ChronoUnit.MONTHS);
            Date lastDate = Date.valueOf(firstPlus);
// Viser hvor meget vi betaler i rente når lånet er slut
            double renten = primoGæld * rente;
            if(i == terminer)
                månedligBetaling = primoGæld + renten;
// Afdrag på gæld
            double afdrag = månedligBetaling - renten;
            double ultimoGæld = primoGæld - afdrag;
            Betalinger [i - 1] = new Invoice(agreement, i, firstDate, lastDate, afdrag, renten, ultimoGæld, primoGæld, månedligBetaling, "");
// Opdatere firstDate og primoGæld for næste iteration
            firstDate = Date.valueOf(firstPlus);
            primoGæld = ultimoGæld;
        }
        return Betalinger;
    }
    public static double renteProcent(Agreement agreement) {
// Finder renten for Kreditværdigheden og dagsrenten
        double dagsRente = agreement.getDaysRate();
        double RKIværdi = switch(agreement.getRki()) {
            case Rating.A -> 1;
            case Rating.B -> 2;
            case Rating.C -> 3;
            default -> 3;
        };
        LocalDate first = agreement.getStart().toLocalDate().plus(3, ChronoUnit.YEARS);
// Vurdere om 1 procentpoint mere i rente hvis startværdien af lånet er mindre end halvdelen af vehicles værdi
        double amountRente = agreement.getStartValue() >= agreement.getVehicle().getPrice() / 2  ? 0 : 1;
        double tidsRente = Date.valueOf(first).getTime() < agreement.getEnd().getTime() ? 1: 0;
// Udregner alle renter sammen
        agreement.setTotalRate(RKIværdi + dagsRente + tidsRente + amountRente);
        return ((RKIværdi + dagsRente + tidsRente + amountRente) / 100)/12;

    }
    public static double fastYdelse(double låneBeløb, double rente, int antalTerminer) {
// Formel for ydelsen
        return (låneBeløb * rente) / (1- Math.pow (1 + rente, -antalTerminer));
    }
}
