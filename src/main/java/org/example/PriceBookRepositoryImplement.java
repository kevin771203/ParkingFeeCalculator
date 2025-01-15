package org.example;

public class PriceBookRepositoryImplement implements PriceBookRepository {

    private PriceBook priceBook;

    public PriceBookRepositoryImplement(PriceBook priceBook) {
        this.priceBook = priceBook;
    }

    @Override
    public PriceBook getPriceBook() {
        return priceBook;
    }

    public void setPriceBook(PriceBook priceBook) {
        this.priceBook = priceBook;
    }
}
