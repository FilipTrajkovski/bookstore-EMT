export const tryParseToPositiveInt = (numberToParse: string): number => {
    const value: number = parseInt(numberToParse);

    return !isNaN(value) ? value : -1;
}

export const convertEnumValue = (value: string): string => {
    return value.charAt(0) + value.slice(1).toLowerCase();
}