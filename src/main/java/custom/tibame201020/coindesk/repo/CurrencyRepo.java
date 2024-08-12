package custom.tibame201020.coindesk.repo;

import custom.tibame201020.coindesk.domain.Currency;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CurrencyRepo extends JpaRepository<Currency, String> {
}
